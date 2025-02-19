-- 코드를 작성해주세요
SELECT count(*) AS COUNT
FROM ECOLI_DATA
WHERE ((GENOTYPE & 1) > 0 or (GENOTYPE & 4) > 0) and (GENOTYPE & 2) = 0