package com.algocrafts.selectors;


import org.openqa.selenium.By;

import java.util.function.Supplier;

public interface SupplierConverter {
    Supplier<By> of(Object value);
}
