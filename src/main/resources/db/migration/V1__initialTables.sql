CREATE TABLE city
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);
INSERT INTO city(id, name)
VALUES (1, 'Ella');
INSERT INTO city(id, name)
VALUES (2, 'Matara');

CREATE TABLE user_profile
(
    id       SERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE patient
(
    patient_id   BIGSERIAL PRIMARY KEY,
    reg_no       TEXT NOT NULL UNIQUE,
    name         TEXT NOT NULL,
    age          INT  NOT NULL,
    gender       TEXT NOT NULL,
    nic          TEXT,
    address      TEXT,
    tp_number    INT,
    allergies    TEXT,
    city_id      INT  NOT NULL,
    created_date TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    updated_date TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    CONSTRAINT fk_patient_city_city_id FOREIGN KEY (city_id) REFERENCES city (id)
);

CREATE TABLE prescription
(
    prescription_id BIGSERIAL PRIMARY KEY,
    patient_id      BIGSERIAL,
    diagnosis       TEXT,
    history         TEXT,
    processed       BOOLEAN,
    created_date    TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    CONSTRAINT fk_prescription_patient_patient_id FOREIGN KEY (patient_id) REFERENCES patient (patient_id) ON DELETE CASCADE
);

CREATE TABLE medicinetype
(
    medicinetype_id BIGSERIAL PRIMARY KEY,
    type            TEXT NOT NULL
);

INSERT INTO medicinetype(medicinetype_id, type)
VALUES (1, 'Pill');
INSERT INTO medicinetype(medicinetype_id, type)
VALUES (2, 'Capsule');
INSERT INTO medicinetype(medicinetype_id, type)
VALUES (3, 'Cream');
INSERT INTO medicinetype(medicinetype_id, type)
VALUES (4, 'Syrup');
INSERT INTO medicinetype(medicinetype_id, type)
VALUES (5, 'Drops');

CREATE TABLE medicine
(
    medicine_id     BIGSERIAL PRIMARY KEY,
    medicinetype_id BIGSERIAL,
    medicine_name   TEXT  NOT NULL,
    unit_price      FLOAT NOT NULL,
    units           INT   NOT NULL,
    minimum_units   INT   NOT NULL,
    city_id         INT   NOT NULL,
    CONSTRAINT fk_medicine_medicinetype_medicinetype_id FOREIGN KEY (medicinetype_id) REFERENCES medicinetype (medicinetype_id),
    CONSTRAINT fk_medicine_city_city_id FOREIGN KEY (city_id) REFERENCES city (id)
);

CREATE TABLE prescription_medicine
(
    prescription_id INT NOT NULL,
    medicine_id     INT NOT NULL,
    dose            TEXT,
    duration        INT,
    frequency       INT,
    frequency_text  TEXT,
    quantity        INT,
    additional_info TEXT,
    PRIMARY KEY (prescription_id, medicine_id),
    CONSTRAINT fk_prescription_medicine_prescription_id FOREIGN KEY (prescription_id) REFERENCES prescription (prescription_id) ON DELETE CASCADE,
    CONSTRAINT fk_prescription_medicine_medicine_id FOREIGN KEY (medicine_id) REFERENCES medicine (medicine_id)
);

CREATE SEQUENCE patient_reg_no_seq START WITH 1;