create table customers(
                          id    bigserial primary key,
                          email varchar(70)  not null,
                          pwd   varchar(500) not null
);

create table roles(
                      id  bigserial primary key,
                      role_name varchar(50),
                      description varchar(100),
                      id_customer bigint,
                      constraint fk_customer foreign key(id_customer) references customers(id)
);

create table partners (
                          id bigserial primary key,
                          client_id varchar(256),
                          client_name varchar(256),
                          client_secret varchar(256),
                          scopes varchar(256),
                          grant_types varchar(256),
                          authentication_methods varchar(256),
                          redirect_uri varchar(256),
                          redirect_uri_logout varchar(256)
);