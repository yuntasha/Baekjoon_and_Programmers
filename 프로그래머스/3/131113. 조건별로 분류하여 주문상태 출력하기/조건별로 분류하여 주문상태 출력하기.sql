-- 코드를 입력하세요
# 2022-05-01기준 조회
# OUT_DATE가 출고 날짜 그거 기준으로 이전이면 출고 완료 이후면 출고 대기 null이면 미정
SELECT ORDER_ID, PRODUCT_ID, DATE_FORMAT(OUT_DATE, "%Y-%m-%d") AS "DATE_FORMAT", CASE
        WHEN OUT_DATE <= "2022-05-01" THEN "출고완료"
        WHEN OUT_DATE IS NULL THEN "출고미정"
        ELSE "출고대기"
        END AS "출고여부"
FROM FOOD_ORDER