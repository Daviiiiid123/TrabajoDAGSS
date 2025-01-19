-- Insert for table administrador
INSERT INTO administrador (id, activo, fechaAlta, login, password, TIPO_USUARIO, ultimoAcceso, email, nombre) VALUES
(1, 1, '2023-01-01 00:00:00', 'admin1', 'password1', 'ADMINISTRADOR', '2023-01-01 00:00:00', 'admin1@example.com', 'Admin One'),
(2, 1, '2023-01-02 00:00:00', 'admin2', 'password2', 'ADMINISTRADOR', '2023-01-02 00:00:00', 'admin2@example.com', 'Admin Two'),
(3, 1, '2023-01-03 00:00:00', 'admin3', 'password3', 'ADMINISTRADOR', '2023-01-03 00:00:00', 'admin3@example.com', 'Admin Three');

-- Insert for table centrosalud
INSERT INTO centrosalud (id, activo, direccion, email, nombre, telefono) VALUES
(1, 1, '123 Main St', 'centro1@example.com', 'Centro Salud Uno', '123456789'),
(2, 1, '456 Elm St', 'centro2@example.com', 'Centro Salud Dos', '987654321'),
(3, 1, '789 Oak St', 'centro3@example.com', 'Centro Salud Tres', '555555555');

-- Insert for table medico
INSERT INTO medico (id, activo, fechaAlta, login, password, TIPO_USUARIO, ultimoAcceso, DNI, apellidos, email, nombre, numeroColegiado, telefono, centroSalud_id) VALUES
(1, 1, '2023-01-01 00:00:00', 'medico1', 'password1', 'MEDICO', '2023-01-01 00:00:00', '12345678B', 'Apellido Dos', 'medico1@example.com', 'Medico Uno', '54321', '987654321', 1),
(2, 1, '2023-01-02 00:00:00', 'medico2', 'password2', 'MEDICO', '2023-01-02 00:00:00', '23456789C', 'Apellido Tres', 'medico2@example.com', 'Medico Dos', '65432', '876543210', 2),
(3, 1, '2023-01-03 00:00:00', 'medico3', 'password3', 'MEDICO', '2023-01-03 00:00:00', '34567890D', 'Apellido Cuatro', 'medico3@example.com', 'Medico Tres', '76543', '765432109', 3);

-- Insert for table paciente
INSERT INTO paciente (id, activo, fechaAlta, login, password, TIPO_USUARIO, ultimoAcceso, DNI, NSS, direccion, email, nombre, telefono, centroSalud_id) VALUES
(1, 1, '2023-01-01 00:00:00', 'paciente1', 'password1', 'PACIENTE', '2023-01-01 00:00:00', '12345678C', 'NSS12345', '456 Main St', 'paciente1@example.com', 'Paciente Uno', '123456789', 1),
(2, 1, '2023-01-02 00:00:00', 'paciente2', 'password2', 'PACIENTE', '2023-01-02 00:00:00', '23456789D', 'NSS23456', '789 Elm St', 'paciente2@example.com', 'Paciente Dos', '987654321', 2),
(3, 1, '2023-01-03 00:00:00', 'paciente3', 'password3', 'PACIENTE', '2023-01-03 00:00:00', '34567890E', 'NSS34567', '101 Pine St', 'paciente3@example.com', 'Paciente Tres', '555555555', 3);

-- Insert for table cita
INSERT INTO cita (id, duracion, estadoCita, fechaHora, medicoAtiende_id, pacienteCitado_id) VALUES
(1, 30, 'PLANIFICADA', '2023-01-01 10:00:00', 1, 1),
(2, 45, 'ANULADA', '2023-01-02 11:00:00', 2, 2),
(3, 60, 'COMPLETADA', '2023-01-03 12:00:00', 3, 3);

-- Insert for table cita_gen
INSERT INTO cita_gen (GEN_NAME, GEN_VAL) VALUES
('CITA_GEN_1', 100),
('CITA_GEN_2', 200),
('CITA_GEN_3', 300);

-- Insert for table farmacia
INSERT INTO farmacia (id, activo, fechaAlta, login, password, TIPO_USUARIO, ultimoAcceso, DNI, apellidosFarmaceutico, direccion, email, nombre, nombreFarmaceutico, numeroColegiado, telefono) VALUES
(1, 1, '2023-01-01 00:00:00', 'farmacia1', 'password1', 'FARMACIA', '2023-01-01 00:00:00', '12345678A', 'Apellido Uno', '123 Main St', 'farmacia1@example.com', 'Farmacia Uno', 'Farmaceutico Uno', '12345', '123456789'),
(2, 1, '2023-01-02 00:00:00', 'farmacia2', 'password2', 'FARMACIA', '2023-01-02 00:00:00', '23456789B', 'Apellido Dos', '456 Elm St', 'farmacia2@example.com', 'Farmacia Dos', 'Farmaceutico Dos', '23456', '987654321'),
(3, 1, '2023-01-03 00:00:00', 'farmacia3', 'password3', 'FARMACIA', '2023-01-03 00:00:00', '34567890C', 'Apellido Tres', '789 Oak St', 'farmacia3@example.com', 'Farmacia Tres', 'Farmaceutico Tres', '34567', '555555555');

-- Insert for table generator_table
INSERT INTO generator_table (GEN_KEY, GEN_VALUE) VALUES
('GEN_KEY_1', 100),
('GEN_KEY_2', 200),
('GEN_KEY_3', 300);

-- Insert for table medicamento
INSERT INTO medicamento (id, activo, dosis, existencias, fabricante, familia, nombreComercial, principioActivo) VALUES
(1, 1, 500, 100, 'Fabricante Uno', 'Familia Uno', 'Medicamento Uno', 'Principio Activo Uno'),
(2, 1, 250, 200, 'Fabricante Dos', 'Familia Dos', 'Medicamento Dos', 'Principio Activo Dos'),
(3, 1, 750, 150, 'Fabricante Tres', 'Familia Tres', 'Medicamento Tres', 'Principio Activo Tres');

-- Insert for table prescripcion
INSERT INTO prescripcion (id, activa, dosis, fechaFin, fechaInicio, indicaciones, medicamento_id, medico_id, paciente_id) VALUES
(1, 1, 500, '2023-01-10 00:00:00', '2023-01-01 00:00:00', 'Tomar una vez al día', 1, 1, 1),
(2, 1, 250, '2023-01-11 00:00:00', '2023-01-02 00:00:00', 'Tomar dos veces al día', 2, 2, 2),
(3, 1, 750, '2023-01-12 00:00:00', '2023-01-03 00:00:00', 'Tomar tres veces al día', 3, 3, 3);

-- Insert for table receta
INSERT INTO receta (id, cantidad, estado, farmaciaServidora, fechaFin, fechaInicio, prescripcion_id) VALUES
(1, 10, 'PLANIFICADA', NULL, '2023-01-10 00:00:00', '2023-01-01 00:00:00', 1),
(2, 20, 'ANULADA', NULL, '2023-01-11 00:00:00', '2023-01-02 00:00:00', 2),
(3, 30, 'SERVIDA', NULL, '2023-01-12 00:00:00', '2023-01-03 00:00:00', 3);

-- Insert for table usuario_gen
INSERT INTO usuario_gen (GEN_NAME, GEN_VAL) VALUES
('USUARIO_GEN_1', 100),
('USUARIO_GEN_2', 200),
('USUARIO_GEN_3', 300);
