$file = "target\SysVentasVehiculo.exe"
$bytes = [System.IO.File]::ReadAllBytes($file)
# Leer offset del PE header
$peOffset = [BitConverter]::ToInt32($bytes, 0x3C)
# Subsystem está en offset +68 del PE Optional Header (PE offset + 4 + 20 + 68)
$subOffset = $peOffset + 4 + 20 + 68
# 2 = GUI (WINDOWS), 3 = CONSOLE
$bytes[$subOffset] = 2
$bytes[$subOffset + 1] = 0
[System.IO.File]::WriteAllBytes($file, $bytes)
Write-Host "Subsistema cambiado a WINDOWS (GUI)"