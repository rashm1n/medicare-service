ALTER TABLE prescriptions
    ADD COLUMN total_price FLOAT;

ALTER TABLE prescription_medicines
    ADD COLUMN price FLOAT;