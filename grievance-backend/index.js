const express = require('express');
const bodyParser = require('body-parser');
const cors = require('cors');
const { Pool } = require('pg');

const app = express();
const port = 8080; // The port your backend will run on

// Middleware
app.use(cors()); // Allow cross-origin requests
app.use(bodyParser.json()); // Parse JSON bodies

// PostgreSQL connection
const pool = new Pool({
  user: 'postgres',     // PostgreSQL username
  host: 'localhost',
  database: 'grievesystem',  // Name of your database
  password: 'root', // PostgreSQL password
  port: 5432,                // PostgreSQL default port
});

// Endpoint to fetch all grievances
app.get('/grievances', async (req, res) => {
  try {
    const result = await pool.query('SELECT * FROM grievances WHERE user_id = $1', [req.query.user_id]);
    res.json(result.rows);
  } catch (error) {
    console.error('Error fetching grievances', error);
    res.status(500).send('Error fetching grievances');
  }
});

app.post('/api/register', async (req, res) => {
  const { username, email, password } = req.body;

  // Logic for checking user existence and registering new user in the database
  // Send success or failure response
});

// Endpoint to submit a new grievance
app.post('/grievances', async (req, res) => {
  const { user_id, type, description } = req.body;

  try {
    const result = await pool.query(
      'INSERT INTO grievances (user_id, type, description) VALUES ($1, $2, $3) RETURNING *',
      [user_id, type, description]
    );
    res.status(201).json(result.rows[0]); // Send back the created grievance
  } catch (error) {
    console.error('Error creating grievance', error);
    res.status(500).send('Error creating grievance');
  }
});
app.post('/api/register', async (req, res) => {
  const { username, email, password } = req.body;

  try {
    // Insert user into the PostgreSQL database
    const result = await pool.query(
      'INSERT INTO users (username, email, password) VALUES ($1, $2, $3) RETURNING *',
      [username, email, password]
    );

    // Respond with success
    res.json({ success: true, user: result.rows[0] });
  } catch (err) {
    console.error(err.message);
    res.status(500).json({ success: false, message: 'Registration failed.' });
  }
});

// Start the server
app.listen(port, () => {
  console.log(`Server running on http://localhost:${port}`);
});
