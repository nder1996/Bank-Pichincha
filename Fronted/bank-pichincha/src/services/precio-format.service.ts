import { DecimalPipe } from '@angular/common';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class PrecioFormatService {

  formatoUSD(value: string): string {
    return this.formatCurrency(value, 'USD');
  }

  formatCOP(value: string): string {
    return this.formatCurrency(value, 'COP');
  }

  private formatCurrency(value: string | number, currencyCode: string): string {
    if (value === null || value === undefined) {
      return '';
    }

    const valueString = typeof value === 'string' ? value : value.toString()
    const numberValue = parseFloat(valueString.replace(/[^0-9.-]/g, ''));

    if (isNaN(numberValue)) {
      return '';
    }

    const formattedValue = numberValue.toFixed(2);

    if (currencyCode === 'USD') {
      return `$ ${formattedValue}`;
    } else if (currencyCode === 'COP') {
      return `COL$ ${formattedValue}`;
    } else {
      return formattedValue;
    }
  }


}
