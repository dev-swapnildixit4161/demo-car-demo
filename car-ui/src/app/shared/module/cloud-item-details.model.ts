import { ThemePalette } from "@angular/material/core";

export interface AvailableCloudOptions {
  label: string;
  path: string;
  icon: string;
  color: ThemePalette;
  isSelected: boolean;
}
