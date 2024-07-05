CREATE TABLE cities
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);
INSERT INTO cities(id, name)
VALUES (1, 'Ella');
INSERT INTO cities(id, name)
VALUES (2, 'Matara');

CREATE TABLE users
(
    id       SERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE patients
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
    CONSTRAINT fk_patients_cities_city_id FOREIGN KEY (city_id) REFERENCES cities (id)
);

CREATE TABLE prescriptions
(
    prescription_id BIGSERIAL PRIMARY KEY,
    patient_id      BIGSERIAL,
    diagnosis       TEXT,
    history         TEXT,
    processed       BOOLEAN,
    created_date    TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    CONSTRAINT fk_prescriptions_patients_patient_id FOREIGN KEY (patient_id) REFERENCES patients (patient_id) ON DELETE CASCADE
);

CREATE TABLE medicinetypes
(
    medicinetype_id BIGSERIAL PRIMARY KEY,
    type            TEXT NOT NULL
);

INSERT INTO medicinetypes(medicinetype_id, type)
VALUES (1, 'Pill');
INSERT INTO medicinetypes(medicinetype_id, type)
VALUES (2, 'Capsule');
INSERT INTO medicinetypes(medicinetype_id, type)
VALUES (3, 'Cream');
INSERT INTO medicinetypes(medicinetype_id, type)
VALUES (4, 'Syrup');
INSERT INTO medicinetypes(medicinetype_id, type)
VALUES (5, 'Drops');

CREATE TABLE medicines
(
    medicine_id     BIGSERIAL PRIMARY KEY,
    medicinetype_id BIGSERIAL,
    medicine_name   TEXT  NOT NULL,
    unit_price      FLOAT NOT NULL,
    units           INT   NOT NULL,
    minimum_units   INT   NOT NULL,
    city_id         INT   NOT NULL,
    CONSTRAINT fk_medicines_medicinetypes_medicinetype_id FOREIGN KEY (medicinetype_id) REFERENCES medicinetypes (medicinetype_id),
    CONSTRAINT fk_medicines_cities_city_id FOREIGN KEY (city_id) REFERENCES cities (id)
);

CREATE TABLE prescription_medicines
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
    CONSTRAINT fk_prescription_medicines_prescription_id FOREIGN KEY (prescription_id) REFERENCES prescriptions (prescription_id) ON DELETE CASCADE,
    CONSTRAINT fk_prescription_medicines_medicine_id FOREIGN KEY (medicine_id) REFERENCES medicines (medicine_id) ON DELETE CASCADE
);

CREATE SEQUENCE patient_ella_reg_no_seq START WITH 1;
CREATE SEQUENCE patient_matara_reg_no_seq START WITH 1;