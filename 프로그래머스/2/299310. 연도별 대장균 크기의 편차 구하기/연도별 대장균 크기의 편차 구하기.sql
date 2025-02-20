-- 코드를 작성해주세요
SELECT B.YEAR AS YEAR, (B.MAX_SIZE - A.SIZE_OF_COLONY) AS YEAR_DEV, A.ID
FROM ECOLI_DATA A
LEFT JOIN (SELECT T.YEAR AS YEAR, MAX(T.SIZE_OF_COLONY) AS MAX_SIZE
    FROM (SELECT YEAR(DIFFERENTIATION_DATE) AS YEAR, SIZE_OF_COLONY, ID
        FROM ECOLI_DATA) T
    GROUP BY T.YEAR) B
ON YEAR(A.DIFFERENTIATION_DATE) = B.YEAR
ORDER BY YEAR, YEAR_DEV