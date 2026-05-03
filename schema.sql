-- ============================================================
-- Snap List — Database Schema
-- Run this file in MySQL Workbench or via the CLI:
--   mysql -u your_username -p snaplist_db < schema.sql
-- ============================================================

CREATE DATABASE IF NOT EXISTS snaplist_db;
USE snaplist_db;

-- ------------------------------------------------------------
-- Users
-- ------------------------------------------------------------
CREATE TABLE IF NOT EXISTS users (
    id            BIGINT          NOT NULL AUTO_INCREMENT,
    username      VARCHAR(100)    NOT NULL,
    password_hash VARCHAR(255)    NOT NULL,
    is_admin      BOOLEAN         NOT NULL DEFAULT FALSE,

    PRIMARY KEY (id),
    UNIQUE KEY uq_username (username)
);

-- ------------------------------------------------------------
-- Cards
-- ------------------------------------------------------------
CREATE TABLE IF NOT EXISTS cards (
    id         BIGINT       NOT NULL AUTO_INCREMENT,
    user_id    BIGINT       NOT NULL,
    name       VARCHAR(255) NOT NULL,
    game       VARCHAR(50)  NOT NULL,
    set_name   VARCHAR(255) NOT NULL,
    language   VARCHAR(50)  NOT NULL,
    `condition` VARCHAR(10) NOT NULL,
    quantity   INT          NOT NULL DEFAULT 1,

    PRIMARY KEY (id),
    CONSTRAINT fk_cards_user
        FOREIGN KEY (user_id) REFERENCES users (id)
        ON DELETE CASCADE
);