INSERT IGNORE INTO games (id, genre, platform, title, price) VALUES
(1, 'RPG', 'PC', 'Starbound Legends', '$14.99'),
(2, 'Simulation', 'PC', 'Systems Integration Quest', '$9.99'),
(3, 'Action', 'PlayStation 5', 'Neon Strike', '$19.99'),
(4, 'Strategy', 'PC', 'Empire Overdrive', '$12.99'),
(5, 'Sports', 'Xbox Series X', 'Rival Pitch', '$24.99'),
(6, 'Horror', 'PlayStation 5', 'Hollow Static', '$17.99'),
(7, 'Adventure', 'Switch', 'Mossgrove Trails', '$9.99'),
(8, 'Puzzle', 'Mobile', 'Tangle Theory', '$2.99'),
(9, 'Racing', 'PC', 'Apex Circuit', '$19.99');

INSERT IGNORE INTO members (id, member_name, member_email) VALUES
(1, 'David Peslak', 'david.peslak@example.com'),
(2, 'Ava Chen', 'ava.chen@example.com');

INSERT IGNORE INTO member_game (member_id, game_id) VALUES
(1, 1), (1, 2), (2, 3);

-- Seed accounts (password shown is the plaintext for demo login purposes):
-- admin / admin123   (ADMIN role, no linked member profile)
-- david / member123  (MEMBER role, linked to member id 1 - David Peslak)
-- ava   / member123  (MEMBER role, linked to member id 2 - Ava Chen)
INSERT IGNORE INTO users (id, username, password, role, member_id) VALUES
(1, 'admin', '$2a$10$/tk8qqN22B.dxnREfepoEONzjrtW0EGRhBg/H50NK8fJfh0B54pCa', 'ADMIN', NULL),
(2, 'david', '$2a$10$XbJ6Ryi7x5HiZjSGeXpepuCw7VEX94U9.WnnwlhLJwm4KJN2ipJ7m', 'MEMBER', 1),
(3, 'ava', '$2a$10$XbJ6Ryi7x5HiZjSGeXpepuCw7VEX94U9.WnnwlhLJwm4KJN2ipJ7m', 'MEMBER', 2);
