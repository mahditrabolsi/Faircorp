INSERT INTO Room(id, name, floor, current_temperature, target_temperature) VALUES(-10, 'Room1', 1, 22.3, 20.0);
INSERT INTO Room(id, name, floor) VALUES(-9, 'Room2', 1);

INSERT INTO Heater(id, heater_status, name, power, room_id) VALUES(-10, 'ON', 'Heater1', 2000, -10);
INSERT INTO Heater(id, heater_status, name, power, room_id) VALUES(-9, 'ON', 'Heater2', null, -10);

INSERT INTO RWindow(id, window_status, name, room_id) VALUES(-10, 'CLOSED', 'Window 1', -10);
INSERT INTO RWindow(id, window_status, name, room_id) VALUES(-9, 'CLOSED', 'Window 2', -10);
INSERT INTO RWindow(id, window_status, name, room_id) VALUES(-8, 'OPEN', 'Window 1', -9);
INSERT INTO RWindow(id, window_status, name, room_id) VALUES(-7, 'CLOSED', 'Window 2', -9);