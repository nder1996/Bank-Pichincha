export class ApiResponse<T> {
    data!: T; // Generico para que puedas usar cualquier tipo de dato
    meta!: Meta;
    error!: ErrorDetails;
  }
  
  export class Meta {
    message!: string;
    status!: number;
  }
  
  export class ErrorDetails {
    code!: string;
    details!: string;
  }