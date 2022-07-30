create table INVOICES
(
    id      uuid default gen_random_uuid() primary key,
    pdf_url varchar(255),
    user_id varchar(255),
    amount  int
);