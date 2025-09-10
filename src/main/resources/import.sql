INSERT INTO task1_parking_session (bay, slot,status) VALUES ('A1','1','FREE');
INSERT INTO task1_parking_session (bay, slot,status) VALUES ('A1','2','FREE');
INSERT INTO task1_parking_session (bay, slot,status) VALUES ('B1','1','FREE');
INSERT INTO task1_parking_session (bay, slot,status) VALUES ('B1','2','FREE');
INSERT INTO task1_parking_session (bay, slot,status) VALUES ('C1','1','FREE');
INSERT INTO task1_parking_session (bay, slot,status) VALUES ('C2','2','FREE');



INSERT INTO bay (id, bay_name, floor) VALUES (1, 'Bay-A', 1);
INSERT INTO bay (id, bay_name, floor) VALUES (2, 'Bay-B', 1);
INSERT INTO bay (id, bay_name, floor) VALUES (3, 'Bay-C', 2);


INSERT INTO slot (id, slot_number, status, bay_id) VALUES (1, 'S101', 'FREE', 1);
INSERT INTO slot (id, slot_number, status, bay_id) VALUES (2, 'S102', 'FREE', 1);
INSERT INTO slot (id, slot_number, status, bay_id) VALUES (3, 'S201', 'FREE', 2);

-- Floors
INSERT INTO floor_car (floor_number, capacity, security_floor) VALUES (1, 10, false);
INSERT INTO floor_car (floor_number, capacity, security_floor) VALUES (2, 8, true);

-- Bays
INSERT INTO bay_car (bay_name, floor) VALUES ('Bay-A', 1);
INSERT INTO bay_car (bay_name, floor) VALUES ('Bay-B', 1);
INSERT INTO bay_car (bay_name, floor) VALUES ('Bay-C', 2);

-- Slots for Bay-A
INSERT INTO slot_car (slot_number, status, bay_id) VALUES ('SLOT-1', 'FREE', 1);
INSERT INTO slot_car (slot_number, status, bay_id) VALUES ('SLOT-2', 'FREE', 1);

-- Slots for Bay-B
INSERT INTO slot_car (slot_number, status, bay_id) VALUES ('SLOT-3', 'FREE', 2);
INSERT INTO slot_car (slot_number, status, bay_id) VALUES ('SLOT-4', 'FREE', 2);

-- Slots for Bay-C
INSERT INTO slot_car (slot_number, status, bay_id) VALUES ('SLOT-5', 'FREE', 3);
INSERT INTO slot_car (slot_number, status, bay_id) VALUES ('SLOT-6', 'FREE', 3);


