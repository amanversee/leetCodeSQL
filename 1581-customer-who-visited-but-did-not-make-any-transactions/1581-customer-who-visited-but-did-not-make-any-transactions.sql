# Write your MySQL query statement below
SELECT v.customer_id, count(v.visit_id) as count_no_trans
FROM visits v
LEFT JOIN transactions t
on t.visit_id = v.visit_id
WHERE t.transaction_id IS NULL
GROUP BY v.customer_id;