CREATE TABLE Owner (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE Pokemon (
    id BIGINT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    height DECIMAL(10,2),
    weight DECIMAL(10,2)
);

CREATE TABLE Team (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    owner_id BIGINT,
    FOREIGN KEY (owner_id) REFERENCES Owner(id)
);

CREATE TABLE team_pokemons (
    team_id BIGINT,
    pokemon_id BIGINT,
    FOREIGN KEY (team_id) REFERENCES Team(id),
    FOREIGN KEY (pokemon_id) REFERENCES Pokemon(id),
    PRIMARY KEY (team_id, pokemon_id)
);

CREATE TABLE owner_teams (
    owner_id BIGINT,
    teams_id BIGINT,
    FOREIGN KEY (owner_id) REFERENCES Owner(id),
    FOREIGN KEY (teams_id) REFERENCES Team(id),
    PRIMARY KEY (owner_id, teams_id)
);
