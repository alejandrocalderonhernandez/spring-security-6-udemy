insert into customers (email, pwd) values
                                       ('account@debuggeandoieas.com', 'to_be_encoded'),
                                       ('cards@debuggeandoieas.com', 'to_be_encoded'),
                                       ('loans@debuggeandoieas.com', 'to_be_encoded'),
                                       ('balance@debuggeandoieas.com', 'to_be_encoded');

insert into roles(role_name, description, id_customer) values
                                                           ('ROLE_ADMIN', 'cant view account endpoint', 1),
                                                           ('ROLE_ADMIN', 'cant view cards endpoint', 2),
                                                           ('ROLE_USER', 'cant view loans endpoint', 3),
                                                           ('ROLE_USER', 'cant view balance endpoint', 4);