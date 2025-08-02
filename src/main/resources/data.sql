-- USERS
INSERT INTO users (name, email, password, enabled)
VALUES
  ('John Doe', 'john@example.com', '{noop}password123', true),
  ('Jane Smith', 'jane@example.com', '{noop}secure-password', true);

-- PLANS
INSERT INTO plans (name, description, price, billing_period)
VALUES
  ('Basic Plan', 'Basic access', 9.99, 'MONTHLY'),
  ('Pro Plan', 'Full access', 99.99, 'YEARLY');

-- SUBSCRIPTIONS
INSERT INTO subscriptions (user_id, plan_id, start_date, end_date, status)
VALUES
  (1, 1, CURRENT_DATE, NULL, 'ACTIVE'),
  (2, 2, CURRENT_DATE, NULL, 'ACTIVE'),
  (1, 2, CURRENT_DATE - 30, CURRENT_DATE, 'CANCELLED');
