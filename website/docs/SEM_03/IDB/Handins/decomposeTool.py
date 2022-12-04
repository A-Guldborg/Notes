SQLquery = """
SELECT 'Project: %s --> %s' AS FD,
CASE WHEN COUNT(*)=0 THEN 'MAY HOLD'
ELSE 'does not hold' END AS VALIDITY
FROM (
    SELECT P.%s
    FROM Projects P
    GROUP BY P.%s
    HAVING COUNT(DISTINCT P.%s) > 1
) X;
"""

def PrintSQL(Att1, Att2):
  print(SQLquery % (Att1, Att2, Att1, Att1, Att2))

cols = ['id', 'pid', 'sid', 'sn', 'pn', 'mid', 'mn']

for i in range(len(cols)):
  for j in range(len(cols)):
    if (i != j):
      PrintSQL(cols[i], cols[j])

