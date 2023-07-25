insert into customers (email, pwd) values
                                       ('account@debuggeandoieas.com', 'to_be_encoded'),
                                       ('cards@debuggeandoieas.com', 'to_be_encoded'),
                                       ('loans@debuggeandoieas.com', 'to_be_encoded'),
                                       ('balance@debuggeandoieas.com', 'to_be_encoded');

insert into roles(role_name, description, id_customer) values
                                                           ('VIEW_ACCOUNT', 'cant view account endpoint', 1),
                                                           ('VIEW_CARDS', 'cant view cards endpoint', 2),
                                                           ('VIEW_LOANS', 'cant view loans endpoint', 3),
                                                           ('VIEW_BALANCE', 'cant view balance endpoint', 4);