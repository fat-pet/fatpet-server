package com.example.fatpetserver.common

import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter
import java.time.YearMonth

@Converter(autoApply = true)
class YearMonthConverter : AttributeConverter<YearMonth, String> {
    override fun convertToDatabaseColumn(attribute: YearMonth): String = attribute.toString()

    override fun convertToEntityAttribute(dbData: String): YearMonth = YearMonth.parse(dbData)
}
