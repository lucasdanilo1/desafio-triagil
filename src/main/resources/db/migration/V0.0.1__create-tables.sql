CREATE TABLE owner (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE pokemon (
    id BIGINT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    height DECIMAL(10,2),
    weight DECIMAL(10,2)
);

CREATE TABLE team (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    owner_id BIGINT,
    FOREIGN KEY (owner_id) REFERENCES owner(id)
);

CREATE TABLE team_pokemons (
    team_id BIGINT,
    pokemon_id BIGINT,
    FOREIGN KEY (team_id) REFERENCES team(id),
    FOREIGN KEY (pokemon_id) REFERENCES pokemon(id),
    PRIMARY KEY (team_id, pokemon_id)
);

CREATE TABLE owner_teams (
    owner_id BIGINT,
    teams_id BIGINT,
    FOREIGN KEY (owner_id) REFERENCES owner(id),
    FOREIGN KEY (teams_id) REFERENCES team(id),
    PRIMARY KEY (owner_id, teams_id)
);
