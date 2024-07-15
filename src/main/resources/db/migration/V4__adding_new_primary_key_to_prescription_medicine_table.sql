ALTER TABLE prescription_medicines
    ADD COLUMN id SERIAL;

ALTER TABLE prescription_medicines
    DROP CONSTRAINT prescription_medicines_pkey;

ALTER TABLE prescription_medicines
    ADD CONSTRAINT prescription_medicines_pkey PRIMARY KEY (id);