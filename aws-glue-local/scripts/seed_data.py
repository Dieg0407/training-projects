import random
import string

def generate_mock_file(filename, row_count):
    print(f"Generating {row_count} rows...")
    
    with open(filename, 'w', encoding='utf-8') as f:
        # 1. Header (Metadata)
        f.write("HEADER|REQ12345|TIMESTAMP:2023-10-27T10:00:00Z|SOURCE:MOCK_GEN\n")
        
        # 2. Main Data Rows
        for i in range(row_count):
            # Generating random sample data for each field
            classification = random.choice(['1', '2', '3'])
            id1 = str(random.randint(1000, 9999))
            id2 = ''.join(random.choices(string.ascii_uppercase + string.digits, k=15))
            id3 = str(random.randint(100, 999))
            id4 = ''.join(random.choices(string.digits, k=15))
            dummy4 = " " * 4
            row_type = random.choice(['1', '2'])
            acc_num = str(random.randint(1000000, 9999999))
            owner = f"USER_{i}".ljust(30)
            amount = str(random.randint(100, 999999)).zfill(10)
            code = random.choice(['A', 'B', 'C'])
            num = str(random.randint(10**19, 10**20 - 1))
            result = random.choice(['0', '1', '2', '3', '4'])
            dummy8 = " " * 8
            
            # Combine using f-strings with fixed widths
            # Format: {value:width} where width is the total length
            line = (
                f"{classification:1s}"
                f"{id1:4s}"
                f"{id2:15s}"
                f"{id3:3s}"
                f"{id4:15s}"
                f"{dummy4:4s}"
                f"{row_type:1s}"
                f"{acc_num:7s}"
                f"{owner:30s}"
                f"{amount:10s}"
                f"{code:1s}"
                f"{num:20s}"
                f"{result:1s}"
                f"{dummy8:8s}"
            )
            
            # Write to file (ensure exactly 120 chars + newline)
            f.write(line + "\n")
            
            if i % 500000 == 0 and i > 0:
                print(f"Progress: {i} rows written...")

        # 3. Footer (Summary)
        f.write(f"FOOTER|TOTAL_ROWS:{row_count}|CHECKSUM:ABC123XYZ\n")

    print(f"Done! Created {filename}")

if __name__ == "__main__":
    generate_mock_file("./generated/mock_data.txt", 2000000)
