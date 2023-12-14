insert into authority values ('779f50c3-a1f5-4d12-9643-32139c14d2ec', 'CAN_PLACE_ORDER'),
                             ('a661a689-f48e-46b7-b29a-48bd0823da23', 'CAN_RETRIEVE_PURCHASE_HISTORY'),
                             ('fcf0114b-88af-4920-8db0-11f50d4e6c0b', 'CAN_RETRIEVE_PRODUCTS');

insert into level values ('686bb227-87b1-43e8-8665-8c2a6e22b25d', 'BRONZE'),
                         ('1d9298ba-588d-47f8-9413-6c55cebdbe78', 'SILVER'),
                         ('4539a4b8-2b16-4dc4-beac-e35b50a5ff46', 'GOLD'),
                         ('73ad8349-fb9d-4571-964e-be1a490093d0', 'PLATINUM'),
                         ('4dab8c9a-9f1f-4c87-b6d4-350e1eca0289', 'DIAMOND');

insert into role values ('164ca30f-9104-4e5a-b871-b4df6444708f', 'CLIENT'),
                        ('f4b3b8a8-c23f-4ab3-830e-a519f478c3c3', 'ADMIN');

ALTER TABLE level
    ADD CONSTRAINT valid_enum_constraint CHECK (name IN ('BRONZE', 'SILVER', 'GOLD', 'PLATINUM', 'DIAMOND'));

ALTER TABLE role
    ADD CONSTRAINT valid_enum_constraint CHECK (name IN ('CLIENT', 'ADMIN'));



/* User soll über Postman localhost:8080/users/register erstellt werden.

   {
    "firstName":"Max",
    "lastName":"Muster",
    "email":"test@gmail.com",
    "password":"password",
    "address":"Peterstrasse 23",
    "ort":"Jona",
    "plz":"8640",
    "birthday":"2000-12-03"
}
 */