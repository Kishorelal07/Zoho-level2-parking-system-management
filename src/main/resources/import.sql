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

