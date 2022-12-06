SELECT * FROM kovalyk.devices
WHERE (SELECT COUNT(*) FROM kovalyk.parts WHERE kovalyk.parts.device = kovalyk.devices.id) > 0;