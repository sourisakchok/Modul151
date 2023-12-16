import axios, { AxiosInstance } from "axios";
import JWTUtility from "./JWTUtility";

export default class AxiosUtility {
    private static api: AxiosInstance;

    public static getApi() {
        if (!this.api) {
            this.api = axios.create({
                baseURL: "http://localhost:8080",
                headers: {
                    "Content-Type": "application/json",
                },
            });
            this.api.interceptors.request.use(
                (config) => {
                    const token = localStorage.getItem("token");
                    if (token && !JWTUtility.checkIfIsExpired(token)) {
                        config.headers.Authorization = `Bearer ${token}`;
                    }
                    return config;
                },
                (error) => Promise.reject(error)
            );
        }
        return this.api;
    }

    public static async authenticate(username: string, password: string): Promise<void> {
        try {
            // Ersetzen Sie 'username' und 'password' durch die tatsächlichen Anmeldedaten.
            const response = await this.api.post("/auth/login", {
                username: username,
                password: password,
            });

            if (response.status === 200) {
                const token = response.data.token; // Stellen Sie sicher, dass die Token-Struktur richtig ist.

                // Speichern Sie das JWT-Token im Local Storage unter dem Schlüssel 'token'.
                localStorage.setItem("token", token);

                console.log("Anmeldung erfolgreich");
            } else {
                console.error("Anmeldung fehlgeschlagen");
            }
        } catch (error) {
            console.error("Fehler bei der Anmeldung", error);
            throw error;
        }
    }
}
