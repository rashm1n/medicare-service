ALTER TABLE prescriptions
    ADD COLUMN consultation_info  TEXT,
    ADD COLUMN consultation_fee   FLOAT,
    ADD COLUMN investigation_info TEXT,
    ADD COLUMN investigation_fee  FLOAT;