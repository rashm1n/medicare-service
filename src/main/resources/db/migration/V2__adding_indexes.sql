CREATE INDEX idx_medicines_city_id ON medicines (city_id);
CREATE INDEX idx_medicines_medicinetype_id ON medicines (medicinetype_id);
CREATE INDEX idx_medicines_minimum_units ON medicines (minimum_units);
CREATE INDEX idx_medicines_units ON medicines (units);
CREATE INDEX idx_medicines_medicine_name ON medicines (LOWER(medicine_name));

CREATE INDEX idx_medicinetypes_type ON medicinetypes (type);

CREATE INDEX idx_patients_city_id ON patients (city_id);
CREATE INDEX idx_patients_name ON patients (LOWER(name));
CREATE INDEX idx_patients_nic ON patients (LOWER(nic));
CREATE INDEX idx_patients_address ON patients (LOWER(address));
CREATE INDEX idx_patients_reg_no ON patients (LOWER(reg_no));

CREATE INDEX idx_prescriptions_patient_id ON prescriptions (patient_id);
CREATE INDEX idx_prescriptions_processed ON prescriptions (processed);
CREATE INDEX idx_prescriptions_created_date ON prescriptions (created_date);

CREATE INDEX idx_prescription_medicines_prescription_id ON prescription_medicines (prescription_id);
CREATE INDEX idx_prescription_medicines_medicine_id ON prescription_medicines (medicine_id);

CREATE INDEX idx_users_username ON users (username);