import base64
import csv
import glob
import os
from io import BytesIO
import shutil
from PIL import Image
from func import get_image_mask, get_image_type

image_folder_output = "c:\\Leonov\\GeniusLociData\\GL_resized\\"
data_folder = "c:\\Leonov\\GeniusLociData\\"
assets_folder = "c:\\Leonov\\GeniusLoci\\app\\src\\main\\assets\\database\\"
data_file = os.path.join(data_folder, "Genius_Loci_DB.csv")
output_text_file = os.path.join(data_folder, "places.csv")
output_images_file = os.path.join(data_folder, "images.csv")

captions = {}
exclude_columns = ["PICS", "ADDRESS_EN"]
output_text_content = []
output_image_content = []

numRow = 0
with open(data_file, encoding='utf-8') as f:
    reader = csv.reader(f)
    for row in reader:
        if numRow == 0:
            col = -1
            for c in row:
                col = col + 1
                if col == 0:
                    continue
                capt = str(c)  # remove_bom(c)
                if capt in exclude_columns:
                    continue
                captions.update({capt: col})
        else:
            lat = row[captions["LATITUDE"]]
            lng = row[captions["LONGITUDE"]]
            id_pic = row[captions["ID"]]
            if len(lat) > 0 and len(lng) > 0 and len(id_pic) > 0:
                # check images
                img_mask = get_image_mask(id_pic)
                path_arg = image_folder_output + img_mask
                img_files = glob.glob(path_arg)
                if len(img_files) > 0:
                    # prepare text part
                    output_str = ""
                    for caption in captions:
                        if len(output_str) > 0:
                            output_str = output_str + ","

                        output_str = output_str + f"'{row[captions[caption]]}'"
                    output_text_content.append(output_str)
                    # prepare images part
                    for img_f in img_files:
                        img_type = get_image_type(img_f)
                        buffered = BytesIO()
                        image = Image.open(os.path.join(image_folder_output, img_f))
                        image.save(buffered, format="JPEG")
                        img_str = base64.b64encode(buffered.getvalue())
                        image_str_out = f"'{id_pic}','{img_type}','{os.path.basename(img_f)}',{img_str}"
                        output_image_content.append(image_str_out)

        numRow = numRow + 1

output_text_content.insert(0, "ID,LATITUDE,LONGITUDE,ADDRESS,NAME_CZ,NAME_EN,NAME_RU,NAME_UA,ABSTRACT_CZ,ABSTRACT_EN,"
                              "ABSTRACT_RU,ABSTRACT_UA,DESCRIPTION_CZ,DESCRIPTION_EN,DESCRIPTION_RU,DESCRIPTION_UA,"
                              "KEY WORDS")
with open(output_text_file, 'w', encoding='utf-8') as f:
    for s in output_text_content:
        f.write(s)
        f.write("\r\n")
print(f"{output_text_file} saved")

with open(output_images_file, 'w', encoding='utf-8') as f:
    for s in output_image_content:
        f.write(s)
        f.write("\r\n")
print(f"{output_images_file} saved")

shutil.copy2(os.path.join(data_folder, "places.csv"), os.path.join(assets_folder, "places.csv"))
shutil.copy2(os.path.join(data_folder, "images.csv"), os.path.join(assets_folder, "images.csv"))
print(f"Files copied to {assets_folder}")

