<?php
declare(strict_types=1);

use Phinx\Migration\AbstractMigration;

final class AlterDisableCoDGreaterThanX extends AbstractMigration
{

    public function change(): void
    {
        $table = $this->table('shop_currencies');
        $table->addColumn('disable_cod', 'boolean')->update();
        $table->addColumn('disable_cod_greater_than', 'boolean')->update();


    }
}
