create table CHL_CHECK_LISTS (
	CHL_ID integer constraint PK_CHL_ID primary key,
	CHL_CODE text constraint UQ_CHL_CODE unique,
	CHL_NAME text,
	CHL_DESCRIPTION text,
	CHL_IMAGE text
);

create table ETR_ENTRIES (
	ETR_ID integer constraint PK_ETR_ID primary key,
	ETR_CODE text constraint UQ_ETR_CODE unique,
	ETR_PRIORITY text,
	ETR_NAME text,
	ETR_DESCRIPTION text,
	ETR_IMAGE text,
	ETR_CHL_ID integer
);

CREATE SEQUENCE CHL_SEQUENCE START 10 increment by 50;
CREATE SEQUENCE ETR_SEQUENCE START 10 increment by 50;