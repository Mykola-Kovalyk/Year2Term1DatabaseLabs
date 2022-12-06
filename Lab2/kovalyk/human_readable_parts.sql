SELECT 
	kovalyk.parts.id, 
    part_number, device, 
    kovalyk.devices.serial_number, 
    kovalyk.parts.manufacturer as part_manufacturer, 
    kovalyk.manufacturers.name as part_manufacturer_name,
    amount
FROM kovalyk.parts
JOIN kovalyk.manufacturers ON kovalyk.manufacturers.id = kovalyk.parts.manufacturer
JOIN kovalyk.devices ON kovalyk.devices.id = kovalyk.parts.device
ORDER BY amount DESC;
