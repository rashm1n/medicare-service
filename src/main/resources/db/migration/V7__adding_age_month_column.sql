ALTER TABLE patients
    ADD COLUMN age_months INT DEFAULT 0;

ALTER TABLE prescription_medicines
    ALTER COLUMN quantity TYPE FLOAT;

ALTER TABLE medicines
    ALTER COLUMN units TYPE FLOAT;