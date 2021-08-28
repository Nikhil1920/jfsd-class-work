CREATE TABLE hostel(
    id INTEGER PRIMARY KEY, 
    first_name VARCHAR(255) NOT NULL, 
    last_name VARCHAR(255) NOT NULL, 
    year_of_study INTEGER NOT NULL, 
    gender VARCHAR(8) NOT NULL, 
    department VARCHAR(3) NOT NULL, 
    mobile VARCHAR(10) NOT NULL, 
    dob DATE NOT NULL, 
    parent_name VARCHAR(255) NOT NULL, 
    parent_mobile_number VARCHAR(10) NOT NULL, 
    address VARCHAR(255) NOT NULL, 
    id_card BLOB NOT NULL, 
    payment_receipet BLOB NOT NULL
);
