-- 코드를 작성해주세요
# 우선 score를 기준으로 점수를 매겨준다
# 그 다음에 저거랑 조인해서 보너스 찾아주기
# 사번으로 조인해서 이름 연결 이건 나중에해도 무방

SELECT G.EMP_NO, E.EMP_NAME, G.GRADE, (CASE
                                      WHEN G.GRADE = "S" THEN E.SAL * 0.2
                                      WHEN G.GRADE = "A" THEN E.SAL * 0.15
                                      WHEN G.GRADE = "B" THEN E.SAL * 0.1
                                      WHEN G.GRADE = "C" THEN 0
                                      END) AS "BONUS"
FROM HR_EMPLOYEES E
JOIN (SELECT EMP_NO, (CASE
                WHEN AVG(SCORE) >= 96 THEN "S"
                WHEN AVG(SCORE) >= 90 THEN "A"
                WHEN AVG(SCORE) >= 80 THEN "B"
                ELSE "C"
                END
) AS "GRADE"
FROM HR_GRADE
GROUP BY EMP_NO) G
ON E.EMP_NO = G.EMP_NO
ORDER BY 1