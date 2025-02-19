-- 코드를 작성해주세요
SELECT ID, CASE 
    WHEN G = 1 THEN "CRITICAL"
    WHEN G = 2 THEN "HIGH"
    WHEN G = 3 THEN "MEDIUM"
    WHEN G = 4 THEN "LOW"
    END AS COLONY_NAME
FROM 
    (SELECT ID, NTILE(4) OVER (ORDER BY SIZE_OF_COLONY DESC) AS G FROM ECOLI_DATA) AS T
ORDER BY ID