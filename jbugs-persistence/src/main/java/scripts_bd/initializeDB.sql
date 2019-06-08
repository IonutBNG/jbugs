
create table users
(
	id bigint auto_increment primary key,
	first_name varchar(255) not null,
	last_name varchar(255) not null,
	mobile_number varchar(255) not null,
	email varchar(255) not null,
	username varchar(255) not null,
	password varchar(255) not null,
	counter int not null
);

create table roles
(
	id bigint auto_increment primary key,
	type varchar(255) not null
);

create table users_roles
(
	user_id bigint not null,
	role_id bigint not null,
	primary key (user_id, role_id),
	constraint FK_users_roles_roles_id
		foreign key (role_id) references roles (id),
	constraint FK_users_roles_users_id
		foreign key (user_id) references users (id)
);

create table permissions
(
	id bigint auto_increment primary key,
	description varchar(255) not null,
	type varchar(255) not null
);

create table roles_permissions
(
	role_id bigint not null,
	permission_id bigint not null,
	primary key (role_id, permission_id),
	constraint FK_permissions_roles_permissions_id
		foreign key (permission_id) references permissions (id),
	constraint FK_permissions_roles_roles_id
		foreign key (role_id) references roles (id)
);

create table bugs
(
    id bigint auto_increment primary key,
    title varchar(255) not null,
    description varchar() not null,
    version varchar(255) not null,
    target_date date not null,
    status varchar(255) not null,
    fixed_version varchar(255) not null,
    severity varchar(255) CHECK(severity="critical" OR severity="high" OR
            severity="medium" OR severity="low"),
    createdByUser bigint not null,
    assignedTo bigint not null,
    constraint FK_bugs_users_created_id
		foreign key (createdByUser) references users (id),
	constraint FK_bugs_users_assigned_id
		foreign key (assignedTo) references users (id)
);

create table tokens (
    id bigint auto_increment primary key,
    token varchar(255) not null,
    user_id int not null,
    expire_time TIME not null,
    constraint FK_tokens_users_id
        foreign key (user_id) references users(id)
)
