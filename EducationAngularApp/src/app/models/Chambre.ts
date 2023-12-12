import { TypeChambre } from "../model/TypeChambre.enum";

export interface Chambre{
  idChambre? :number;
  nomBloc ?: string;
  numChambre?: number;
  typeChambre ?:TypeChambre;
 }
