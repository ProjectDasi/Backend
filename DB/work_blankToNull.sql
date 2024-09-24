SELECT * FROM dasi.work;

SET SQL_SAFE_UPDATES = 0;

UPDATE dasi.work
SET
subtitle = NULLIF(subtitle, ''),
salary = NULLIF(salary, ''),
due_date = NULLIF(due_date, ''),
career = NULLIF(career, ''),
education = NULLIF(education, ''),
work_type = NULLIF(work_type, ''),
work_category = NULLIF(work_category, ''),
contact = NULLIF(contact, ''),
work_hours = NULLIF(work_hours, ''),
details = NULLIF(details, ''),
email = NULLIF(email, ''),
certification = NULLIF(certification, ''),
preference_id = NULLIF(preference_id, ''),
region_id = NULLIF(region_id, '');

SET SQL_SAFE_UPDATES = 1;
