USE [Library]
GO
/****** Object:  Table [dbo].[Books]    Script Date: 5/3/2022 9:58:39 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Books](
	[IdBook] [varchar](15) NOT NULL,
	[BookTitle] [nvarchar](50) NOT NULL,
	[Author] [nvarchar](50) NULL,
	[PublishingHouse] [nvarchar](50) NULL,
	[PublishingYear] [char](4) NULL,
	[ReprintTimes] [int] NULL,
	[Category] [nvarchar] (50) NULL,
	[NumberOfBook] [int] NOT NULL,
 CONSTRAINT [PK_Books] PRIMARY KEY CLUSTERED 
(
	[IdBook] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CallCard]    Script Date: 5/3/2022 9:58:39 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CallCard](
	[IdCallCard] [varchar](50) NOT NULL,
	[IdReader] [varchar](15) NOT NULL,
	[IdStaff] [int] NOT NULL,
	[BookLoanDay] [date] NOT NULL,
 CONSTRAINT [PK_CallCard] PRIMARY KEY CLUSTERED 
(
	[IdCallCard] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CallCardInformation]    Script Date: 5/3/2022 9:58:39 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CallCardInformation](
	[IdCallCard] [varchar](50) NOT NULL,
	[IdBook] [varchar](15) NOT NULL,
	[ReturnDealine] [date] NOT NULL,
 CONSTRAINT [PK_CallCardInformation] PRIMARY KEY CLUSTERED 
(
	[IdCallCard] ASC,
	[IdBook] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Readers]    Script Date: 5/3/2022 9:58:39 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Readers](
	[IdReader] [varchar](15) NOT NULL,
	[NameReader] [nvarchar](50) NOT NULL,
	[Address] [nvarchar](50) NULL,
	[Email] [varchar](30) NULL,
	[PhoneNumber] [varchar](10) NULL,
	[Expiry] [date] NOT NULL,
	[IsLock] [bit] NULL,
 CONSTRAINT [PK_Readers] PRIMARY KEY CLUSTERED 
(
	[IdReader] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ReturnCard]    Script Date: 5/3/2022 9:58:39 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ReturnCard](
	[IdCallCard] [varchar](50) NOT NULL,
	[IdBook] [varchar](15) NOT NULL,
	[IdStaff] [int] NOT NULL,
	[ReturnDate] [date] NOT NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Staff]    Script Date: 5/3/2022 9:58:39 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Staff](
	[IdStaff] [int] IDENTITY(1,1) NOT NULL,
	[NameStaff] [nvarchar](50) NOT NULL,
	[Username] [nvarchar](50) NOT NULL,
	[Password] [nvarchar](50) NOT NULL,
	[Address] [nvarchar](50) NULL,
	[Email] [varchar](30) NULL,
	[PhoneNumber] [varchar](11) NULL,
 CONSTRAINT [PK_Staff] PRIMARY KEY CLUSTERED 
(
	[IdStaff] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[CallCard]  WITH CHECK ADD  CONSTRAINT [FK_Readers_CallCard] FOREIGN KEY([IdReader])
REFERENCES [dbo].[Readers] ([IdReader])
GO
ALTER TABLE [dbo].[CallCard] CHECK CONSTRAINT [FK_Readers_CallCard]
GO
ALTER TABLE [dbo].[CallCard]  WITH CHECK ADD  CONSTRAINT [FK_Staff_CallCard] FOREIGN KEY([IdStaff])
REFERENCES [dbo].[Staff] ([IdStaff])
GO
ALTER TABLE [dbo].[CallCard] CHECK CONSTRAINT [FK_Staff_CallCard]
GO
ALTER TABLE [dbo].[CallCardInformation]  WITH CHECK ADD  CONSTRAINT [FK_Books_CallCardInformation] FOREIGN KEY([IdBook])
REFERENCES [dbo].[Books] ([IdBook])
GO
ALTER TABLE [dbo].[CallCardInformation] CHECK CONSTRAINT [FK_Books_CallCardInformation]
GO
ALTER TABLE [dbo].[CallCardInformation]  WITH CHECK ADD  CONSTRAINT [FK_CallCard_CallCardInformation] FOREIGN KEY([IdCallCard])
REFERENCES [dbo].[CallCard] ([IdCallCard])
GO
ALTER TABLE [dbo].[CallCardInformation] CHECK CONSTRAINT [FK_CallCard_CallCardInformation]
GO
ALTER TABLE [dbo].[ReturnCard]  WITH CHECK ADD  CONSTRAINT [FK_Books_ReturnCard] FOREIGN KEY([IdBook])
REFERENCES [dbo].[Books] ([IdBook])
GO
ALTER TABLE [dbo].[ReturnCard] CHECK CONSTRAINT [FK_Books_ReturnCard]
GO
ALTER TABLE [dbo].[ReturnCard]  WITH CHECK ADD  CONSTRAINT [FK_CallCard_ReturnCard] FOREIGN KEY([IdCallCard])
REFERENCES [dbo].[CallCard] ([IdCallCard])
GO
ALTER TABLE [dbo].[ReturnCard] CHECK CONSTRAINT [FK_CallCard_ReturnCard]
GO
ALTER TABLE [dbo].[ReturnCard]  WITH CHECK ADD  CONSTRAINT [FK_Staff_ReturnCard] FOREIGN KEY([IdStaff])
REFERENCES [dbo].[Staff] ([IdStaff])
GO
ALTER TABLE [dbo].[ReturnCard] CHECK CONSTRAINT [FK_Staff_ReturnCard]
GO
