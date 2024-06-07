CREATE TABLE patient
(
    patient_id   BIGSERIAL PRIMARY KEY,
    patient_code TEXT NOT NULL,
    patient_name TEXT NOT NULL,
    age          INT  NOT NULL,
    gender       TEXT NOT NULL,
    nic          TEXT,
    address      TEXT,
    tp_number    INT,
    allergies    TEXT,
    created_date TIMESTAMP WITH TIME ZONE DEFAULT NOW()
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
    CONSTRAINT fk_medicine_medicinetype_medicinetype_id FOREIGN KEY (medicinetype_id) REFERENCES medicinetype (medicinetype_id)
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