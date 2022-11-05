Insert into building(id,name, address) values(-10, 'Building 1', 'Address1');
Insert into building(id,name, address) values(-9, 'Building 2', 'Address2');
INSERT into building(id,name, address) values(-8, 'Building 3', 'Address3');

INSERT INTO Room(id, name, floor, current_temperature, target_temperature,BUILDING_ID) VALUES(-10, 'Room1', 1, 22.3, 20.0,-10);
INSERT INTO Room(id, name, floor,BUILDING_ID) VALUES(-9, 'Room 1', 1,-9);
INSERT INTO Room(id, name, floor,BUILDING_ID) VALUES(-8, 'Room 2', 2,-9);

INSERT INTO Room(id, name, floor, current_temperature, target_temperature,BUILDING_ID) VALUES(-7, 'Room 1', 1, 25.3, 22.0,-8);

INSERT INTO Heater(id, heater_status, name, power, room_id) VALUES(-10, 'ON', 'Heater 1', 2000, -10);
INSERT INTO Heater(id, heater_status, name, power, room_id) VALUES(-9, 'ON', 'Heater 2', 1000, -9);
INSERT INTO Heater(id, heater_status, name, power, room_id) VALUES(-8, 'OFF', 'Heater 3', 2000, -9);
INSERT INTO Heater(id, heater_status, name, power, room_id) VALUES(-7, 'OFF', 'Heater 4', null, -10);
INSERT INTO Heater(id, heater_status, name, power, room_id) VALUES(-6, 'ON', 'Heater 1', 2000, -7);
INSERT INTO Heater(id, heater_status, name, power, room_id) VALUES(-5, 'ON', 'Heater 2', 1000, -8);
INSERT INTO Heater(id, heater_status, name, power, room_id) VALUES(-4, 'OFF', 'Heater 3', 2000, -9);
INSERT INTO Heater(id, heater_status, name, power, room_id) VALUES(-3, 'OFF', 'Heater 4', null, -9);

INSERT INTO RWindow(id, window_status, name, room_id) VALUES(-10, 'CLOSED', 'Window 1', -10);
INSERT INTO RWindow(id, window_status, name, room_id) VALUES(-9, 'CLOSED', 'Window 2', -10);
INSERT INTO RWindow(id, window_status, name, room_id) VALUES(-8, 'OPEN', 'Window 3', -9);
INSERT INTO RWindow(id, window_status, name, room_id) VALUES(-7, 'CLOSED', 'Window 4', -9);
INSERT INTO RWindow(id, window_status, name, room_id) VALUES(-6, 'CLOSED', 'Window 5', -8);
INSERT INTO RWindow(id, window_status, name, room_id) VALUES(-3, 'CLOSED', 'Window 6', -8);
INSERT INTO RWindow(id, window_status, name, room_id) VALUES(-5, 'OPEN', 'Window 7', -7);
INSERT INTO RWindow(id, window_status, name, room_id) VALUES(-4, 'CLOSED', 'Window 8', -7);
