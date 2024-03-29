package it.nanaki92.salestaxes.billing;

import java.math.BigDecimal;
import java.math.RoundingMode;

import it.nanaki92.salestaxes.warehouse.Item;

import static it.nanaki92.salestaxes.warehouse.Category.*;

public class Tax {
	private final int standardTax;
	private final int importedTax;
	
	public Tax(int standardTax, int importedTax) {
		this.standardTax = standardTax;
		this.importedTax = importedTax;
	}
	
	public BigDecimal calculateTaxOnItem(Item item) {
		int taxRate = taxRatePerItem(item);
		BigDecimal price = item.getNetPrice();
		
		BigDecimal taxOnItem = price.divide(new BigDecimal(100)).multiply(new BigDecimal(taxRate));
		taxOnItem = roundToNearestFiveCent(taxOnItem);
		
		taxOnItem = taxOnItem.setScale(2, RoundingMode.HALF_UP);
		return taxOnItem;
	}
	
	private int taxRatePerItem(Item item) {
		int tax = 0;		
		
		if (isTaxed(item)) {
			tax += standardTax;
		}
		if (item.isImported()) {
			tax += importedTax;
		}
		
		return tax;
	}
	
	private boolean isTaxed(Item item) {
		if (item.getCategory() == OTHER) {
			return true;
		}
		return false;
	}
	
	private BigDecimal roundToNearestFiveCent(BigDecimal num) {
		return num.multiply(new BigDecimal(20.00)).setScale(0, RoundingMode.UP).divide(new BigDecimal(20.00));
	}
	
}
