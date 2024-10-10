const { Pool } = require('pg');

// Configure your PostgreSQL database connection here
const pool = new Pool({
  user: 'postgres',         // Replace with your PostgreSQL username
  host: 'localhost',
  database: 'grievancemanagement',     // Replace with your PostgreSQL database name
  password: 'So@5112002', // Replace with your PostgreSQL password
  port: 5432,                   // Default PostgreSQL port
});

module.exports = {
  query: (text, params) => pool.query(text, params),
};
