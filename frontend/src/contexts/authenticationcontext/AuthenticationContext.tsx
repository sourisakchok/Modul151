import React, {createContext, ReactNode, useContext, useEffect, useReducer, useState} from "react";
import User from "../../models/User";
import {Navigate, redirect} from "react-router-dom";
import {AxiosInstance} from "axios";
import AxiosUtility from "../../utility/AxiosUtility";
import Authority from "../../models/Authority";

interface AuthenticationContextProps {
  principal: User | undefined;
  hasAnyAuthority: (authorities: Authority["name"][]) => boolean;
  logout: () => Promise<void>;
}

enum ActionTypes {
  LOADING,
  FAILED,
  AUTHENTICATED
}

export const AuthenticationContext = createContext<AuthenticationContextProps>(
    {} as AuthenticationContextProps
);

export const useAuth = () => {
  return useContext(AuthenticationContext);
};

export interface AuthenticationContextProviderProps {
  children: ReactNode;
}

const AuthenticationContextProvider = ({children}: AuthenticationContextProviderProps) => {
  const api: AxiosInstance = AxiosUtility.getApi()
  const [principal, setPrincipal] = useState<User | undefined>();
  const reducer = (state: ActionTypes, action: ActionTypes) => {
    switch (action) {
      case ActionTypes.LOADING:
        return ActionTypes.LOADING;
      case ActionTypes.FAILED:
        return ActionTypes.FAILED;
      case ActionTypes.AUTHENTICATED:
        return ActionTypes.AUTHENTICATED;
    }
  }
  const [state, dispatch] = useReducer(reducer, ActionTypes.LOADING)

  const authenticate = async () => {
    try {
      const response = await api.post('/users/login', {"email": "max@mustermann.com","password": "Souri1234#"});
      // console.log(response);
      if (response.headers['authorization']) {
        localStorage.setItem('token', response.headers['authorization']);
        const userProfileResponse = await api.get('/users/profile');
        // console.log(userProfileResponse);
        if (userProfileResponse.status === 200) {
          // console.log(userProfileResponse.data)
          setPrincipal(userProfileResponse.data);
          dispatch(ActionTypes.AUTHENTICATED);
        } else {
          dispatch(ActionTypes.FAILED);
        }
      } else {
        dispatch(ActionTypes.FAILED);
      }
    } catch {
      dispatch(ActionTypes.FAILED);
    }
  }

  useEffect(() => {
    authenticate()
  }, [])

  const hasAnyAuthority = (authorities: Authority["name"][]): boolean => {
    if (principal === undefined) {
      return false;
    }
    const result = getAuthorities(principal);
    return authorities.every(authorities =>
        result.some(userAuthority => userAuthority.name === authorities)
    );
  }

  function getAuthorities(user: User): Authority[] {
    const authorities = user.role.authorities;
    return Array.from(new Set(authorities.map((authority) => authority.id)))
        .map((authorityId) => authorities.find((authority) => authority.id === authorityId) as Authority);
  }

  const logout = async () => {
    setPrincipal(undefined);
    redirect("/login")
  }

  const renderContent = () => {
    switch (state) {
      case ActionTypes.LOADING:
        return <p>LOADING...</p>;
      case ActionTypes.FAILED:
        return <Navigate to={"/login"}/>
      case ActionTypes.AUTHENTICATED:
        return children;
    }
  }

  return (
      <AuthenticationContext.Provider
          value={{
            principal,
            hasAnyAuthority,
            logout
          }}
      >
        {renderContent()}
      </AuthenticationContext.Provider>
  );
};

export default AuthenticationContextProvider;
