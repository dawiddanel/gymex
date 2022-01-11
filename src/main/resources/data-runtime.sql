INSERT INTO public.activity_definition (id, description, level, name) VALUES (1, 'System walki opracowany do samoobrony', 'INTERMEDIATE', 'Krav Maga');
INSERT INTO public.activity_definition (id, description, level, name) VALUES (2, 'System filozofii indyjskiej', 'BEGINNER', 'Joga');
INSERT INTO public.activity_definition (id, description, level, name) VALUES (3, 'Połączenie jogi, baletu oraz ćwiczeń izometrycznych', 'BEGINNER', 'Pilates');
INSERT INTO public.gym (id, building_number, city, country, postal_code, street, capacity, name) VALUES (1, '14', 'Rybnik', 'PL', '44-200', 'Makaronowa', 50, 'K1 Fitness');
INSERT INTO public.userr (id, active, created_date, email, password, role, username) VALUES (1, true, (NOW() - interval '1 day'), 'owner@test.pl', '$2a$10$WtVRRJeiwVaytdnj5zrerusC66VOUHdZeZ8HcK0szs2USie5kCFaS', 'OWNER', 'owner');
INSERT INTO public.userr (id, active, created_date, email, password, role, username) VALUES (2, true, (NOW() - interval '1 day'), 'trainer@test.pl', '$2a$10$0vlQCyuKm0bfJReAoFtlBuMj0w1lT1xlpQr9L5nzCyG0O6DADWB0q', 'TRAINER', 'trainer');
INSERT INTO public.userr (id, active, created_date, email, password, role, username) VALUES (3, true, (NOW() - interval '1 day'), 'member@test.pl', '$2a$10$VkJE1FDreyRX9SxsflbGiuoWp2WxtykghO5kdS00VTh4ZMlsdXjmu', 'MEMBER', 'member');
INSERT INTO public.userr (id, active, created_date, email, password, role, username) VALUES (4, true, (NOW() - interval '1 day'), 'employee@test.pl', '$2a$10$13p9s/r.TwEONVWhUePd2uhMp8k07r26zR/cGPQBhNgUq3MOYhWE6', 'EMPLOYEE', 'employee');
INSERT INTO public.person (person_type, birth_date, firstname, lastname, pesel, user_id) VALUES ('OWNER', '1996-11-28', 'Kamil', 'Testowy', '96112835838', 1);
INSERT INTO public.person (person_type, birth_date, firstname, lastname, pesel, user_id) VALUES ('TRAINER', '1996-11-28', 'Mateusz', 'Karat', '96112897432', 2);
INSERT INTO public.person (person_type, birth_date, firstname, lastname, pesel, user_id) VALUES ('MEMBER', '1996-11-28', 'Dawid', 'Amant', '96112890435', 3);
INSERT INTO public.person (person_type, birth_date, firstname, lastname, pesel, user_id) VALUES ('EMPLOYEE', '1996-11-28', 'Katarzyna', 'Kot', '96112821343', 4);
INSERT INTO public.pass (id, active, end_date, start_date, member_user_id) VALUES (1, true, (NOW() - interval '1 day'), (NOW() + interval '31 day'), 3);
INSERT INTO public.presence (id, end_time, start_time, gym_id, member_user_id) VALUES (1, (NOW() - interval '1 day'), (NOW() - interval '1 day 1 hour'), 1, 3);
INSERT INTO public.presence (id, end_time, start_time, gym_id, member_user_id) VALUES (2, null, (NOW() - interval '1 hour'), 1, 3);
INSERT INTO public.timetable (id, end_date, order_number, start_date, update_date, gym_id) VALUES (1, (NOW() + interval '13 day'), 1, (NOW() - interval '1 day'), (NOW() - interval '1 day'), 1);
INSERT INTO public.assortment (update_date, gym_id) VALUES ((NOW() - interval '1 day'), 1);
INSERT INTO public.equipment_definition (id, aimed_body_parts, description, name, purpose, type, weight) VALUES (1, 'ANY', 'Odpowiednia do trójboju siłowego', 'Sztanga Eleiko', 'STRENGTH', 'BARBELL', 20);
INSERT INTO public.equipment_definition (id, aimed_body_parts, description, name, purpose, type, weight) VALUES (2, 'LEGS-CALVES', 'Wysokie zaangażowanie mięśni nóg', 'Suwnica', 'STRENGTH', 'MACHINE', NULL);
INSERT INTO public.equipment_definition (id, aimed_body_parts, description, name, purpose, type, weight) VALUES (3, 'ANY', 'Odpowiednia do treningu kardio, chodzenia i biegania', 'Bieżnia', 'CARDIO', 'MACHINE', NULL);
INSERT INTO public.equipment (id, quantity, assortment_gym_id, definition_id) VALUES (1, 4, 1, 1);
INSERT INTO public.equipment (id, quantity, assortment_gym_id, definition_id) VALUES (2, 1, 1, 2);
INSERT INTO public.equipment (id, quantity, assortment_gym_id, definition_id) VALUES (3, 10, 1, 3);
INSERT INTO public.activity (id, capacity, end_time, start_time, status, definition_id, timetable_id, trainer_user_id) VALUES (3, 20, (NOW() - interval '1 day'), (NOW() - interval '1 day 1 hour'), 'FINISHED', 3, 1, 2);
INSERT INTO public.activity (id, capacity, end_time, start_time, status, definition_id, timetable_id, trainer_user_id) VALUES (2, 20, (NOW() + interval '2 hour'), (NOW() + interval '1 hour'), 'CREATED', 2, 1, 2);
INSERT INTO public.activity (id, capacity, end_time, start_time, status, definition_id, timetable_id, trainer_user_id) VALUES (1, 15, (NOW() - interval '5 hour'), (NOW() - interval '6 hour'), 'CANCELLED', 1, 1, 2);
INSERT INTO public.activity_member (activity_id, member_id) VALUES (2, 3);
INSERT INTO public.activity_member (activity_id, member_id) VALUES (3, 3);
INSERT INTO public.attendance (id, attended, activity_id, member_user_id) VALUES (1, true, 3, 3);
INSERT INTO public.attendance (id, attended, activity_id, member_user_id) VALUES (2, null, 2, 3);