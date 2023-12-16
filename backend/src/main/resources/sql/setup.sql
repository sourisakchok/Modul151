insert into authority values ('779f50c3-a1f5-4d12-9643-32139c14d2ec', 'CAN_PLACE_ORDER'),
                             ('a661a689-f48e-46b7-b29a-48bd0823da23', 'CAN_RETRIEVE_PURCHASE_HISTORY'),
                             ('fcf0114b-88af-4920-8db0-11f50d4e6c0b', 'CAN_RETRIEVE_PRODUCTS');

insert into level values ('686bb227-87b1-43e8-8665-8c2a6e22b25d', 'BRONZE', 0.00, 0),
                         ('1d9298ba-588d-47f8-9413-6c55cebdbe78', 'SILVER', 0.04, 20),
                         ('4539a4b8-2b16-4dc4-beac-e35b50a5ff46', 'GOLD', 0.07, 60),
                         ('73ad8349-fb9d-4571-964e-be1a490093d0', 'PLATINUM', 0.09, 140),
                         ('4dab8c9a-9f1f-4c87-b6d4-350e1eca0289', 'DIAMOND', 0.11, 300);

insert into role values ('164ca30f-9104-4e5a-b871-b4df6444708f', 'CLIENT'),
                        ('f4b3b8a8-c23f-4ab3-830e-a519f478c3c3', 'ADMIN');

INSERT INTO product VALUES
    ('123e4567-e89b-12d3-a456-426614174001', 'Snowflake', 15.99, '2023-01-01', 'China', 10.99, 15.99),
    ('223e4567-e89b-12d3-a456-426614174002', 'Mistral', 18.99, '2023-02-15', 'Taiwan', 12.99, 18.99),
    ('323e4567-e89b-12d3-a456-426614174003', 'Emerald Dew', 12.99, '2023-03-20', 'Japan', 8.99, 12.99),
    ('423e4567-e89b-12d3-a456-426614174004', 'Dragons Roast', 16.99, '2023-04-10', 'China', 11.99, 16.99),
  ('523e4567-e89b-12d3-a456-426614174005', 'Silk Sunset', 22.99, '2023-05-05', 'Taiwan', 15.99, 22.99),
  ('623e4567-e89b-12d3-a456-426614174006', 'Moonlit Jasmine', 19.99, '2023-06-30', 'Japan', 13.99, 19.99),
  ('723e4567-e89b-12d3-a456-426614174007', 'Ebony Elixir', 14.99, '2023-07-15', 'China', 9.99, 14.99),
  ('823e4567-e89b-12d3-a456-426614174008', 'Golden Horizon', 21.99, '2023-08-25', 'Taiwan', 14.99, 21.99),
  ('923e4567-e89b-12d3-a456-426614174009', 'Healing Herb Blend', 25.99, '2023-09-10', 'Japan', 18.99, 25.99),
  ('a23e4567-e89b-12d3-a456-426614174010', 'Eternal Blossom', 29.99, '2023-10-05', 'China', 20.99, 29.99);

INSERT INTO category values
                         ('562ef1c8-57a3-447a-93cf-d5f03012a330', 'White'),
                         ('f0ded9c3-d2c2-4c1c-b806-1a4947b4f759', 'Green'),
                         ('774f8953-abc4-4274-87f1-d8fa5b5edb56', 'Oolong'),
                         ('22674f3e-7e77-4884-b157-57a80f11e412', 'Black'),
                         ('81ede6ba-419d-4c48-90fe-8682982c8182', 'Medicinal Herbs');


ALTER TABLE level
    ADD CONSTRAINT valid_enum_constraint CHECK (name IN ('BRONZE', 'SILVER', 'GOLD', 'PLATINUM', 'DIAMOND'));

ALTER TABLE role
    ADD CONSTRAINT valid_enum_constraint CHECK (name IN ('CLIENT', 'ADMIN'));



/* User soll über Postman localhost:8080/users/register erstellt werden.
   {
    "firstName":"Zinedine",
    "lastName":"Tomasella",
    "email":"test@gmail.com",
    "password":"Password1%",
    "address":"Peterstrasse 34",
    "ort":"Jona",
    "plz":"8640",
    "birthday":"2000-02-23"
}
 */