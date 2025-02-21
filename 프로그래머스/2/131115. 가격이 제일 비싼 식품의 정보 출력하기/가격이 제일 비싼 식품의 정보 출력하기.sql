-- 코드를 입력하세요
# 테이블에서 가장 비싼 식품
SELECT *
FROM FOOD_PRODUCT
WHERE PRICE = (SELECT MAX(PRICE)
              FROM FOOD_PRODUCT)