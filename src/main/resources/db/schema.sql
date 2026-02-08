-- Eliminar la tabla si existe para evitar errores en reinicios de H2
DROP TABLE IF EXISTS PRICES;

CREATE TABLE PRICES (
    ID          BIGINT AUTO_INCREMENT PRIMARY KEY,
    BRAND_ID    INT NOT NULL,
    START_DATE  TIMESTAMP NOT NULL,
    END_DATE    TIMESTAMP NOT NULL,
    PRICE_LIST  INT NOT NULL,
    PRODUCT_ID  BIGINT NOT NULL,
    PRIORITY    INT NOT NULL,
    PRICE       DECIMAL(10, 2) NOT NULL,
    CURR        VARCHAR(3) NOT NULL
);

-- Comentario: 
-- BRAND_ID: 1 Marca 1
-- PRICE: Usamos DECIMAL para precisión monetaria (evita errores de redondeo de Double/Float)
-- TIMESTAMP: Compatible con LocalDateTime de Java

CREATE INDEX IDX_PRICES
ON PRICES (BRAND_ID, START_DATE, END_DATE, PRODUCT_ID);