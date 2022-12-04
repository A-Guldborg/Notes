```sql
SELECT 'Project: id --> pid' AS FD,
CASE WHEN COUNT(*)=0 THEN 'MAY HOLD'
ELSE 'does not hold' END AS VALIDITY
FROM (
    SELECT P.id
    FROM Projects P
    GROUP BY P.id
    HAVING COUNT(DISTINCT P.pid) > 1
) X;
```

```sql
SELECT 'Project: id --> sid' AS FD,
CASE WHEN COUNT(*)=0 THEN 'MAY HOLD'
ELSE 'does not hold' END AS VALIDITY
FROM (
    SELECT P.id
    FROM Projects P
    GROUP BY P.id
    HAVING COUNT(DISTINCT P.sid) > 1
) X;
```

```sql
SELECT 'Project: id --> sn' AS FD,
CASE WHEN COUNT(*)=0 THEN 'MAY HOLD'
ELSE 'does not hold' END AS VALIDITY
FROM (
    SELECT P.id
    FROM Projects P
    GROUP BY P.id
    HAVING COUNT(DISTINCT P.sn) > 1
) X;
```

```sql
SELECT 'Project: id --> pn' AS FD,
CASE WHEN COUNT(*)=0 THEN 'MAY HOLD'
ELSE 'does not hold' END AS VALIDITY
FROM (
    SELECT P.id
    FROM Projects P
    GROUP BY P.id
    HAVING COUNT(DISTINCT P.pn) > 1
) X;

```

```sql
SELECT 'Project: id --> mid' AS FD,
CASE WHEN COUNT(*)=0 THEN 'MAY HOLD'
ELSE 'does not hold' END AS VALIDITY
FROM (
    SELECT P.id
    FROM Projects P
    GROUP BY P.id
    HAVING COUNT(DISTINCT P.mid) > 1
) X;

```

```sql
SELECT 'Project: id --> mn' AS FD,
CASE WHEN COUNT(*)=0 THEN 'MAY HOLD'
ELSE 'does not hold' END AS VALIDITY
FROM (
    SELECT P.id
    FROM Projects P
    GROUP BY P.id
    HAVING COUNT(DISTINCT P.mn) > 1
) X;

```

```sql
SELECT 'Project: pid --> id' AS FD,
CASE WHEN COUNT(*)=0 THEN 'MAY HOLD'
ELSE 'does not hold' END AS VALIDITY
FROM (
    SELECT P.pid
    FROM Projects P
    GROUP BY P.pid
    HAVING COUNT(DISTINCT P.id) > 1
) X;
```

```sql
SELECT 'Project: pid --> sid' AS FD,
CASE WHEN COUNT(*)=0 THEN 'MAY HOLD'
ELSE 'does not hold' END AS VALIDITY
FROM (
    SELECT P.pid
    FROM Projects P
    GROUP BY P.pid
    HAVING COUNT(DISTINCT P.sid) > 1
) X;
```

```sql
SELECT 'Project: pid --> sn' AS FD,
CASE WHEN COUNT(*)=0 THEN 'MAY HOLD'
ELSE 'does not hold' END AS VALIDITY
FROM (
    SELECT P.pid
    FROM Projects P
    GROUP BY P.pid
    HAVING COUNT(DISTINCT P.sn) > 1
) X;
```

```sql
SELECT 'Project: pid --> pn' AS FD,
CASE WHEN COUNT(*)=0 THEN 'MAY HOLD'
ELSE 'does not hold' END AS VALIDITY
FROM (
    SELECT P.pid
    FROM Projects P
    GROUP BY P.pid
    HAVING COUNT(DISTINCT P.pn) > 1
) X;
```

```sql
SELECT 'Project: pid --> mid' AS FD,
CASE WHEN COUNT(*)=0 THEN 'MAY HOLD'
ELSE 'does not hold' END AS VALIDITY
FROM (
    SELECT P.pid
    FROM Projects P
    GROUP BY P.pid
    HAVING COUNT(DISTINCT P.mid) > 1
) X;
```

```sql
SELECT 'Project: pid --> mn' AS FD,
CASE WHEN COUNT(*)=0 THEN 'MAY HOLD'
ELSE 'does not hold' END AS VALIDITY
FROM (
    SELECT P.pid
    FROM Projects P
    GROUP BY P.pid
    HAVING COUNT(DISTINCT P.mn) > 1
) X;
```

```sql
SELECT 'Project: sid --> id' AS FD,
CASE WHEN COUNT(*)=0 THEN 'MAY HOLD'
ELSE 'does not hold' END AS VALIDITY
FROM (
    SELECT P.sid
    FROM Projects P
    GROUP BY P.sid
    HAVING COUNT(DISTINCT P.id) > 1
) X;
```

```sql
SELECT 'Project: sid --> pid' AS FD,
CASE WHEN COUNT(*)=0 THEN 'MAY HOLD'
ELSE 'does not hold' END AS VALIDITY
FROM (
    SELECT P.sid
    FROM Projects P
    GROUP BY P.sid
    HAVING COUNT(DISTINCT P.pid) > 1
) X;
```

```sql
SELECT 'Project: sid --> sn' AS FD,
CASE WHEN COUNT(*)=0 THEN 'MAY HOLD'
ELSE 'does not hold' END AS VALIDITY
FROM (
    SELECT P.sid
    FROM Projects P
    GROUP BY P.sid
    HAVING COUNT(DISTINCT P.sn) > 1
) X;
```

```sql
SELECT 'Project: sid --> pn' AS FD,
CASE WHEN COUNT(*)=0 THEN 'MAY HOLD'
ELSE 'does not hold' END AS VALIDITY
FROM (
    SELECT P.sid
    FROM Projects P
    GROUP BY P.sid
    HAVING COUNT(DISTINCT P.pn) > 1
) X;
```

```sql
SELECT 'Project: sid --> mid' AS FD,
CASE WHEN COUNT(*)=0 THEN 'MAY HOLD'
ELSE 'does not hold' END AS VALIDITY
FROM (
    SELECT P.sid
    FROM Projects P
    GROUP BY P.sid
    HAVING COUNT(DISTINCT P.mid) > 1
) X;
```

```sql
SELECT 'Project: sid --> mn' AS FD,
CASE WHEN COUNT(*)=0 THEN 'MAY HOLD'
ELSE 'does not hold' END AS VALIDITY
FROM (
    SELECT P.sid
    FROM Projects P
    GROUP BY P.sid
    HAVING COUNT(DISTINCT P.mn) > 1
) X;
```

```sql
SELECT 'Project: sn --> id' AS FD,
CASE WHEN COUNT(*)=0 THEN 'MAY HOLD'
ELSE 'does not hold' END AS VALIDITY
FROM (
    SELECT P.sn
    FROM Projects P
    GROUP BY P.sn
    HAVING COUNT(DISTINCT P.id) > 1
) X;
```

```sql
SELECT 'Project: sn --> pid' AS FD,
CASE WHEN COUNT(*)=0 THEN 'MAY HOLD'
ELSE 'does not hold' END AS VALIDITY
FROM (
    SELECT P.sn
    FROM Projects P
    GROUP BY P.sn
    HAVING COUNT(DISTINCT P.pid) > 1
) X;
```

```sql
SELECT 'Project: sn --> sid' AS FD,
CASE WHEN COUNT(*)=0 THEN 'MAY HOLD'
ELSE 'does not hold' END AS VALIDITY
FROM (
    SELECT P.sn
    FROM Projects P
    GROUP BY P.sn
    HAVING COUNT(DISTINCT P.sid) > 1
) X;
```

```sql
SELECT 'Project: sn --> pn' AS FD,
CASE WHEN COUNT(*)=0 THEN 'MAY HOLD'
ELSE 'does not hold' END AS VALIDITY
FROM (
    SELECT P.sn
    FROM Projects P
    GROUP BY P.sn
    HAVING COUNT(DISTINCT P.pn) > 1
) X;
```

```sql
SELECT 'Project: sn --> mid' AS FD,
CASE WHEN COUNT(*)=0 THEN 'MAY HOLD'
ELSE 'does not hold' END AS VALIDITY
FROM (
    SELECT P.sn
    FROM Projects P
    GROUP BY P.sn
    HAVING COUNT(DISTINCT P.mid) > 1
) X;
```

```sql
SELECT 'Project: sn --> mn' AS FD,
CASE WHEN COUNT(*)=0 THEN 'MAY HOLD'
ELSE 'does not hold' END AS VALIDITY
FROM (
    SELECT P.sn
    FROM Projects P
    GROUP BY P.sn
    HAVING COUNT(DISTINCT P.mn) > 1
) X;
```

```sql
SELECT 'Project: pn --> id' AS FD,
CASE WHEN COUNT(*)=0 THEN 'MAY HOLD'
ELSE 'does not hold' END AS VALIDITY
FROM (
    SELECT P.pn
    FROM Projects P
    GROUP BY P.pn
    HAVING COUNT(DISTINCT P.id) > 1
) X;
```

```sql
SELECT 'Project: pn --> pid' AS FD,
CASE WHEN COUNT(*)=0 THEN 'MAY HOLD'
ELSE 'does not hold' END AS VALIDITY
FROM (
    SELECT P.pn
    FROM Projects P
    GROUP BY P.pn
    HAVING COUNT(DISTINCT P.pid) > 1
) X;
```

```sql
SELECT 'Project: pn --> sid' AS FD,
CASE WHEN COUNT(*)=0 THEN 'MAY HOLD'
ELSE 'does not hold' END AS VALIDITY
FROM (
    SELECT P.pn
    FROM Projects P
    GROUP BY P.pn
    HAVING COUNT(DISTINCT P.sid) > 1
) X;
```

```sql
SELECT 'Project: pn --> sn' AS FD,
CASE WHEN COUNT(*)=0 THEN 'MAY HOLD'
ELSE 'does not hold' END AS VALIDITY
FROM (
    SELECT P.pn
    FROM Projects P
    GROUP BY P.pn
    HAVING COUNT(DISTINCT P.sn) > 1
) X;
```

```sql
SELECT 'Project: pn --> mid' AS FD,
CASE WHEN COUNT(*)=0 THEN 'MAY HOLD'
ELSE 'does not hold' END AS VALIDITY
FROM (
    SELECT P.pn
    FROM Projects P
    GROUP BY P.pn
    HAVING COUNT(DISTINCT P.mid) > 1
) X;
```

```sql
SELECT 'Project: pn --> mn' AS FD,
CASE WHEN COUNT(*)=0 THEN 'MAY HOLD'
ELSE 'does not hold' END AS VALIDITY
FROM (
    SELECT P.pn
    FROM Projects P
    GROUP BY P.pn
    HAVING COUNT(DISTINCT P.mn) > 1
) X;
```

```sql
SELECT 'Project: mid --> id' AS FD,
CASE WHEN COUNT(*)=0 THEN 'MAY HOLD'
ELSE 'does not hold' END AS VALIDITY
FROM (
    SELECT P.mid
    FROM Projects P
    GROUP BY P.mid
    HAVING COUNT(DISTINCT P.id) > 1
) X;
```

```sql
SELECT 'Project: mid --> pid' AS FD,
CASE WHEN COUNT(*)=0 THEN 'MAY HOLD'
ELSE 'does not hold' END AS VALIDITY
FROM (
    SELECT P.mid
    FROM Projects P
    GROUP BY P.mid
    HAVING COUNT(DISTINCT P.pid) > 1
) X;
```

```sql
SELECT 'Project: mid --> sid' AS FD,
CASE WHEN COUNT(*)=0 THEN 'MAY HOLD'
ELSE 'does not hold' END AS VALIDITY
FROM (
    SELECT P.mid
    FROM Projects P
    GROUP BY P.mid
    HAVING COUNT(DISTINCT P.sid) > 1
) X;
```

```sql
SELECT 'Project: mid --> sn' AS FD,
CASE WHEN COUNT(*)=0 THEN 'MAY HOLD'
ELSE 'does not hold' END AS VALIDITY
FROM (
    SELECT P.mid
    FROM Projects P
    GROUP BY P.mid
    HAVING COUNT(DISTINCT P.sn) > 1
) X;
```

```sql
SELECT 'Project: mid --> pn' AS FD,
CASE WHEN COUNT(*)=0 THEN 'MAY HOLD'
ELSE 'does not hold' END AS VALIDITY
FROM (
    SELECT P.mid
    FROM Projects P
    GROUP BY P.mid
    HAVING COUNT(DISTINCT P.pn) > 1
) X;
```

```sql
SELECT 'Project: mid --> mn' AS FD,
CASE WHEN COUNT(*)=0 THEN 'MAY HOLD'
ELSE 'does not hold' END AS VALIDITY
FROM (
    SELECT P.mid
    FROM Projects P
    GROUP BY P.mid
    HAVING COUNT(DISTINCT P.mn) > 1
) X;
```

```sql
SELECT 'Project: mn --> id' AS FD,
CASE WHEN COUNT(*)=0 THEN 'MAY HOLD'
ELSE 'does not hold' END AS VALIDITY
FROM (
    SELECT P.mn
    FROM Projects P
    GROUP BY P.mn
    HAVING COUNT(DISTINCT P.id) > 1
) X;
```

```sql
SELECT 'Project: mn --> pid' AS FD,
CASE WHEN COUNT(*)=0 THEN 'MAY HOLD'
ELSE 'does not hold' END AS VALIDITY
FROM (
    SELECT P.mn
    FROM Projects P
    GROUP BY P.mn
    HAVING COUNT(DISTINCT P.pid) > 1
) X;
```

```sql
SELECT 'Project: mn --> sid' AS FD,
CASE WHEN COUNT(*)=0 THEN 'MAY HOLD'
ELSE 'does not hold' END AS VALIDITY
FROM (
    SELECT P.mn
    FROM Projects P
    GROUP BY P.mn
    HAVING COUNT(DISTINCT P.sid) > 1
) X;
```

```sql
SELECT 'Project: mn --> sn' AS FD,
CASE WHEN COUNT(*)=0 THEN 'MAY HOLD'
ELSE 'does not hold' END AS VALIDITY
FROM (
    SELECT P.mn
    FROM Projects P
    GROUP BY P.mn
    HAVING COUNT(DISTINCT P.sn) > 1
) X;
```

```sql
SELECT 'Project: mn --> pn' AS FD,
CASE WHEN COUNT(*)=0 THEN 'MAY HOLD'
ELSE 'does not hold' END AS VALIDITY
FROM (
    SELECT P.mn
    FROM Projects P
    GROUP BY P.mn
    HAVING COUNT(DISTINCT P.pn) > 1
) X;
```

```sql
SELECT 'Project: mn --> mid' AS FD,
CASE WHEN COUNT(*)=0 THEN 'MAY HOLD'
ELSE 'does not hold' END AS VALIDITY
FROM (
    SELECT P.mn
    FROM Projects P
    GROUP BY P.mn
    HAVING COUNT(DISTINCT P.mid) > 1
) X;
```
